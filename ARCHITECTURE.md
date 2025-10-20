# ARCHITECTURE

## Decisões Arquiteturais

* **Padrão adotado**:
  MVI (Model-View-Intent) combinado com Clean Architecture simplificada.

  * **MVI**: padrão arquitetural para Android que usa um fluxo de dados unidirecional para gerenciar o estado.
  * **Clean Architecture simplificada**: só Data Layer + ViewModel, sem Domain Layer, por simplicidade e por regras de negócio simples.

---

### Camadas

| Camada                 | Responsabilidade                                                                                                                                 | Limites                                                                |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------- |
| **App** | Main Activity                                                                                                                                                   | Apenas com main nav para navegação entre fragments. |
| **Feature** | Fragments e Compose, UI e ViewModels. Observa `StateFlow` da ViewModel e renderiza o state da UI.                                                           | Não acessa Data Layer diretamente, mas chama Repository via ViewModel. |
| **Data / Repository**  | Fornece dados para ViewModel, abstrai fonte de dados. Implementa Repository Pattern (`MenuRepositoryImpl`) e DataSource (`MenuLocalDataSource`). | Apenas entrega Flow de dados. Não contém lógica de UI.                 |
| **Core**               | Classes utilitárias, helpers.                                                                                                                    | Pode ser usado em qualquer parte do projeto.                           |

---

### DI (Injeção de Dependência)

* **Escolha**: **Hilt**
* **Motivo**:

  * Facilita injeção de Repository e DataSource em ViewModels.
  * Permite mockar facilmente dependências em testes.
  * Mantém desacoplamento e aumenta testabilidade.

---

### Threading / Reatividade

* **Coroutines + Flow**

  * **Repository / DataSource**: `flowOn(Dispatchers.IO)` para I/O seguro (leitura de JSON).
  * **ViewModel**: coleta `Flow` e atualiza `StateFlow`.
* **Motivo**:

  * Flow permite propagação assíncrona de dados, fácil teste e gerenciamento de estados.
  * Mantém UI responsiva sem bloqueios.
* **StateFlow** para gerenciamento de estados reativo (`Idle → Loading → Success → Error`).

---

### Testabilidade e Qualidade

* **DataLayer**: mock do `JsonReader`, teste de Flow com `runTest`.
* **Repository**: testa propagação do Flow, threading e exceções.
* **ViewModel**: mock do Repository, valida transições de `StateFlow`.
* **Qualidade implementada**:

  * `flowOn(Dispatchers.IO)` garante thread correta.
  * Injeção de dependência com Hilt garante desacoplamento.

---

## Trade-offs Relevantes

* **Domain Layer ausente**:

  * Trade-off: menos desacoplamento entre regras de negócio e Repository.
  * Justificativa: regras simples, apenas leitura de JSON e exibição em UI.
  * Evita boilerplate e aumenta velocidade de desenvolvimento.

---

## O que foi evitado / decisões relevantes

* **Não usei Domain Layer**: porque a complexidade de regras não justificava.
* **Testes focados em Data + ViewModel**: fluxo de dados e estado testável sem Android real.
* **Decisões importantes**:

  * Refactor de Activities para Fragments com Compose.
  * Repository Pattern para desacoplamento da fonte de dados.
  * MVI para uma arquitetura que gerencia os estados da view com Compose.
  * StateFlow para UI reativa.
  * Hilt para DI e testabilidade.
  * Coroutines + Dispatchers.IO para I/O seguro e threads não bloqueantes.


