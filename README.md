# Robô Marciano - Exercício Kotlin

Projeto desenvolvido como requisito avaliativo para a disciplina de **Linguagens de Programação Móvel**. 
O objetivo deste projeto é construir um sistema interativo via console simulando um robô ("Marciano") capaz de processar linguagem natural básica, realizar operações matemáticas e executar injeção de dependências através de ações personalizadas.

## Funcionalidades

O sistema foi modularizado em três camadas de complexidade:

1. **Interação Básica:** Processamento de texto usando Expressões Regulares (Regex) para identificar perguntas, gritos (maiúsculas) e palavras-chave específicas (ex: "eu").
2. **Módulo Matemático:** Capacidade de extrair operandos numéricos da entrada do usuário para realizar adição, subtração, multiplicação e divisão.
3. **Módulo Premium:** Interceptação de comandos para acionar ações customizadas definidas dinamicamente durante a instanciação do robô.

## Conceitos de Orientação a Objetos Aplicados

Para atender aos critérios de qualidade e robustez do código, o projeto faz uso extensivo de recursos avançados do Kotlin:

* **Herança (`open class`):** Estruturação hierárquica das classes (`RoboMarciano` > `RoboMarcianoAvancado` > `RoboMarcianoPremium`) garantindo o reaproveitamento de código e a modularização.
* **Sobrecarga de Métodos (Overload):** Múltiplas assinaturas para a função `responda()`, permitindo que o sistema diferencie entradas de texto de requisições matemáticas com base nos parâmetros recebidos.
* **Sobrescrita (Override):** Modificação do comportamento da função principal na classe Premium, com o uso de chamadas `super` para delegar tratativas que não pertencem ao escopo da classe atual.
* **Interfaces e Injeção de Dependência:** Uso da interface `AcaoPersonalizada` como um contrato rígido, permitindo que a versão Premium execute rotinas externas (como interações com banco de dados ou APIs) sem conhecer a implementação interna dessas ações.
