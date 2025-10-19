# ARCHITECTURE

## Decisões Arquiteturais

* **Padrão adotado**:
  MVVM (Model-View-ViewModel) combinado com Clean Architecture simplificada.

  * **MVVM**: separa UI, estado e lógica de apresentação.
  * **Clean Architecture simplificada**: só Data Layer + ViewModel, sem Domain Layer, por simplicidade e por regras de negócio simples.

---

### Camadas

| Camada                 | Responsabilidade                                                                                                                                 | Limites                                                                |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------- |
| **App / Presentation** | Activities, UI e ViewModels. Observa `StateFlow` da ViewModel e renderiza estados (`Idle`, `Loading`, `Success`, `Error`).            | Não acessa Data Layer diretamente, mas chama Repository via ViewModel. |
| **Data / Repository**  | Fornece dados para ViewModel, abstrai fonte de dados. Implementa Repository Pattern (`MenuRepositoryImpl`) e DataSource (`MenuLocalDataSource`). | Apenas entrega Flow de dados. Não contém lógica de UI.                 |
| **Core**               | Classes utilitárias, helpers e JsonReader.                                                                                                       | Pode ser usado em qualquer parte do projeto.                           |

> Observação: features (`menu-list`, `menu-detail`) estão **dentro do módulo `app`**, sem módulos separados.

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
  * Estado inicial `Idle` evita inconsistências na UI.

---

## Trade-offs Relevantes

* **Domain Layer ausente**:

  * Trade-off: menos desacoplamento entre regras de negócio e Repository.
  * Justificativa: regras simples, apenas leitura de JSON e exibição em UI.
  * Evita boilerplate e aumenta velocidade de desenvolvimento.

* **Features dentro do módulo `app`**:

  * Trade-off: menos isolamento de features.
  * Justificativa: projeto pequeno/legado, não há necessidade de modularização completa.

---

## O que foi evitado / decisões relevantes

* **Evitei reescrever código legado**: Tela de detalhes e parte do layout XML mantidos.
* **Não usei Domain Layer**: porque a complexidade de regras não justificava.
* **Testes focados em Data + ViewModel**: fluxo de dados e estado testável sem Android real.
* **Decisões importantes**:

  * Repository Pattern para desacoplamento da fonte de dados.
  * BaseActivity e BaseViewModel para controle de estados
  * StateFlow para UI reativa.
  * Hilt para DI e testabilidade.
  * Coroutines + Dispatchers.IO para I/O seguro e threads não bloqueantes.


