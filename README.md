# Cadápio Android

Este é um projeto com o objetivo de migrar o fluxo de listas de produtos para Kotlin, mantendo os layouts em XML.
Também há versões bonus com Fragments e Compose ou somente Jetpack Compose, com algumas mudanças na arquitetura por exemplo MVI e também alguns modulos (Design System, Navigation e Domain), basta acessar as branchs [enh/menu-fragment-compose](https://github.com/Jvrni/job-dev-android-legacy-migration/tree/enh/menu-fragment-compose) ou [enh/menu-only-compose](https://github.com/Jvrni/job-dev-android-legacy-migration/tree/enh/menu-only-compose)

- App name: Cadápio Android
- Package: `com.goomer.ps`
- Min SDK: 25 (Android 7.1)
- Mock de dados local em `assets/menu.json`

## Como rodar
- Android Studio (versão estável atual) com JDK 17
- Abrir a pasta `cardapio-app/` no Android Studio
- Sincronizar Gradle
- Rodar o app no emulador/dispositivo: módulo `app` (Build/Run padrão)
- Testes unitários **./gradlew test**
- Rodar ktlint **./gradlew ktlintCheck**
- Rodar detekt **./gradlew detekt**

## Tech stack & Open-source libraries
- Kotlin, Coroutines flow + StateFlow for asynchronous, UnitTests, CI.

- **Jetpack**
    - View Binding: Um recurso que simplifica a interação com visualizações no seu layout XML.
    - ViewModel: Gerencia o detentor de dados relacionados à interface do usuário e o ciclo de vida. Permite que os dados sobrevivam a alterações de configuração, como rotações de tela.
    - Hilt: Para injeção de dependencia.

- **Arquitetura**
    - MVVM (Model - View - ViewModel)
    - Module Pattern
    - Repository Pattern
  
## Architecture overview

A arquitetura ou o plano de migração você pode ver nos arquivos [ARCHITECTURE.md](https://github.com/Jvrni/job-dev-android-legacy-migration/blob/enh/menu/ARCHITECTURE.md) and [MIGRATION_PLAN.md](https://github.com/Jvrni/job-dev-android-legacy-migration/blob/enh/menu/MIGRATION_PLAN.md)
