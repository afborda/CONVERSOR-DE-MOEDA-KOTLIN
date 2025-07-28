
# Currency Type Manager

Este é um aplicativo Android desenvolvido em Kotlin que permite visualizar diferentes tipos de moedas com suas respectivas bandeiras de países em formato SVG.

## 📱 Funcionalidades

- Exibição de lista de tipos de moedas
- Visualização de bandeiras de países em formato SVG
- Interface customizada com adapter personalizado
- Carregamento eficiente de imagens com a biblioteca Coil

## 🏗️ Arquitetura do Projeto

### 📂 Estrutura Principal (`app/`)

#### **Arquivos de Configuração**
- **`build.gradle.kts`**: Configurações do módulo app, dependências (Coil, Coil-SVG) e plugins necessários
- **`proguard-rules.pro`**: Regras de ofuscação e otimização para builds de produção
- **`AndroidManifest.xml`**: Configurações do aplicativo, permissões e declaração de componentes

#### **Código-Fonte (`src/main/kotlin/`)**

**Adapters:**
- **`CurrencyTypeAdapter.kt`**: Adapter customizado para ListView que gerencia a exibição dos tipos de moedas
  - Implementa `getView()` para inflar layouts personalizados
  - Integra Coil com suporte a SVG para carregamento de bandeiras
  - Usa ViewBinding para acesso otimizado às views

**Models/Data Classes:**
- **`CurrencyType.kt`**: Classe de dados que representa um tipo de moeda
  - Contém propriedades como `acronym` (sigla da moeda)
  - `contryFlagImageUrl` (URL da bandeira do país em SVG)

**Activities/Fragments:**
- Arquivos principais que gerenciam as telas do aplicativo
- Implementam a lógica de negócio e interação com o usuário

#### **Recursos (`src/main/res/`)**

**Layouts:**
- **`item_currency_type.xml`**: Layout do item individual da lista de moedas
  - Contém `TextView` para sigla da moeda (`tvCurrencyAcronym`)
  - `ImageView` para bandeira do país (`ivFlag`)

**Values:**
- **`strings.xml`**: Textos e labels do aplicativo
- **`colors.xml`**: Paleta de cores do tema
- **`themes.xml`**: Definições de temas e estilos

#### **View Binding**
- **`ItemCurrencyTypeBinding`**: Classe gerada automaticamente pelo View Binding
  - Fornece acesso type-safe às views do layout
  - Elimina a necessidade de `findViewById()`

## 🔧 Dependências Principais

```kotlin
// Carregamento de imagens
implementation("io.coil-kt:coil:2.6.0")
implementation("io.coil-kt:coil-svg:2.6.0") // Suporte para SVG

// View Binding (habilitado no build.gradle.kts)
buildFeatures {
    viewBinding = true
}
