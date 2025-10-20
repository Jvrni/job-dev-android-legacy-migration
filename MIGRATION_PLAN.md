# MIGRATION_PLAN

Migrar o app legado para Kotlin, adotando **MVI + Clean simplificado**, **Repository Pattern**, **StateFlow** e **Hilt**, garantindo reatividade, testabilidade e refatoração incremental, sem Domain Layer.

---

### Análise do Legado

- Mapear **Activities** ou **Fragments** existentes.  
- Identificar **AsyncTasks**, callbacks e acesso direto a dados.  
- Listar funcionalidades críticas e telas prioritárias para migração.  
- Mapear fontes de dados:
  - JSON local 
  - API  
  - Banco de dados
    
---

### Preparação do Projeto

- Habilitar Kotlin no Gradle.  
- Configurar **Hilt** para injeção de dependência.  
- Criar estrutura de pastas baseada em camadas:
  - `app` → Activity
  - `feature` → Fragments, Compose e ViewModels
  - `data` → Repositories e DataSources  
  - `core` → Helpers, utilitários  
- Manter código legado isolado inicialmente, permitindo **integração incremental**.

---

### Migrar Repositórios e DataSources

- Criar **Repositories Kotlin** para cada fluxo de dados, por exemplo:
  - `MenuRepositoryImpl` + `MenuLocalDataSource`  
- Substituir **AsyncTask** por **Flow + Coroutines**
- Implementar propagação de erros e estados.

---

### Criar ViewModels e Gerenciar Estados

- Criar **ViewModels Kotlin** para cada tela, por exemplo: `MenuListViewModel`.  
- Usar **MVI** fluxo de dados unidirecional para gerenciar o estado de UI.  
- Integrar ViewModels com **Repositories** usando **Flow**.  

---

### Migração Incremental de UI 
- Refatorar tela para Kotlin gradualmente, mantendo layouts XML existentes. (Opção também de manter **Activities Java** inicialmente. )

---

### Testes

- **DataLayer**: testar **Flow**, thread IO e propagação de dados.  
- **Repository**: mock do MenuLocalDataSource.  
- **ViewModel**: mock do Repository, verificar transições de **StateFlow**.  
- Usar `runTest` para **coroutines + Flow**.

---

### Threading e Reatividade

- Substituir **AsyncTask** por **Coroutines + Flow**.  
- Garantir `flowOn(Dispatchers.IO)` para I/O seguro.  
- UI observa **StateFlow** e se mantém responsiva.

---

### Refatoração Final

- Gradual substituição de **Activities Java** por Fragments com Compose em Kotlin.   
- Remover código legado e integração completa.

