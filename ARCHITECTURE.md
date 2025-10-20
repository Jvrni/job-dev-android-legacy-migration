# ARCHITECTURE

## Decisões Arquiteturais

* **Padrão adotado**:
  MVI (Model-View-Intent) combinado com Clean Architecture.

  * **MVI**: padrão arquitetural para Android que usa um fluxo de dados unidirecional para gerenciar o estado.
  * **Clean Architecture**: Domain Layer, Data Layer e Presentation Layer (Feature).

---

### Camadas

| Camada                 | Responsabilidade                                                                                                                                 | Limites                                                                |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------- |
| **App** | Main Activity                                                                                                                                                   | Apenas com main nav para navegação entre composables. |
| **Feature** | Compose Screens e Graph, e ViewModels. Observa `StateFlow` da ViewModel e renderiza o state da UI.                                                          | Não acessa Data Layer diretamente, mas chama use case via ViewModel. |
| **Domain / Use Case**  | Fornece dados para ViewModel e pode conter regras de negócio.                                                                                    | Pode gerenciar regras de negócio e entrega de dados. Não contém lógica de UI.
| **Data / Repository**  | Fornece dados para Domain Layer, abstrai fonte de dados. Implementa repository e data source.                                                    | Apenas entrega Flow de dados. Não contém lógica de UI.                 |
| **Core**               | Classes utilitárias, Design system, Navigation e helpers.                                                                                        | Pode ser usado em qualquer parte do projeto.                           |

---

### DI (Injeção de Dependência)

* **Escolha**: **Hilt**
* **Motivo**:

  * Facilita injeção de Use Cases, Repository e DataSource em ViewModels.
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
* **StateFlow** para gerenciamento de estados reativo.

---

### Testabilidade e Qualidade

* **DataLayer**: mock do `JsonReader`, teste de Flow com `runTest`.
* **Repository**: testa propagação do Flow, threading e exceções.
* **ViewModel**: mock do Repository, valida transições de `StateFlow`.
* **Qualidade implementada**:

  * `flowOn(Dispatchers.IO)` garante thread correta.
  * Injeção de dependência com Hilt garante desacoplamento.

---

## O que foi evitado / decisões relevantes

* **Testes focados em Data + ViewModel**: fluxo de dados e estado testável sem Android real.
* **Decisões importantes**:

  * Refactor de Activities para Compose.
  * Repository Pattern para desacoplamento da fonte de dados.
  * MVI para uma arquitetura que gerencia os estados da view com Compose.
  * StateFlow para UI reativa.
  * Hilt para DI e testabilidade.
  * Coroutines + Dispatchers.IO para I/O seguro e threads não bloqueantes.
