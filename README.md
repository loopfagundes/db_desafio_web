
## Desafio Tecnico DBServer

### [Bug Bank](https://bugbank.netlify.app/)

>*O banco com bugs e falhas do seu jeito.*
>
>*É um Bug Bank, para que? É pratique de testes e automação de testes.*

---
### :clipboard: Visão Geral

Este projeto foi desenvolvido exclusivamente para [DBServer](https://db.tec.br/) e tem como objetivo demonstrar competências em testes de software.

---

### :wrench: Tecnologias e Ferramentas Utilizadas

#### Estrutura:

:pushpin: [MAVEN](https://maven.apache.org/download.cgi) - Gerenciador de dependências e automação de build.
    
:pushpin: [JAVA 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Linguagem de programação principal do projeto.

#### Dependencias:
:pushpin: [TestNG](https://mvnrepository.com/artifact/org.testng/testng/7.11.0) - Framework de teste. 

:pushpin: [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.29.0) - Automatação de testes para aplicações web.

:pushpin: [WebDriverManager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/5.9.0) - Gerenciamento de drivers do navegador.

:pushpin: [Extent Reports TestNG Adapter](https://mvnrepository.com/artifact/com.aventstack/extentreports-testng-adapter/1.0.3) - Geração de relatórios de execução.

:pushpin: [Java Faker](https://mvnrepository.com/artifact/com.github.javafaker/javafaker/1.0.2) - Geração de dados fictícios para testes.

:pushpin: [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.36) - Redução de boilerplate no código.

---
#### Clonando o Repositório:

Execute o seguinte comando em seu terminal ou utilize sua IDE favorita:

```
git clone https://github.com/loopfagundes/db_desafio.git
```

#### Executando os Testes:

Para rodar os testes localmente, utilize os comandos abaixo:

  
```
mvn clean
``` 

```
mvn install -DskipTests=true
```

```
mvn test
```

Por padrão, os testes serão executados no navegador Chrome. Para alternar para outros navegadores (Firefox ou Edge), edite o seguinte arquivo:

  
```
src/main/java/app/netlify/bugbank/utils/BaseTest
```

```
@BeforeMethod
DriverFactory.createInstance(BrowserEnum.CHROME);
```

Substitua `BrowserEnum.CHROME` por `BrowserEnum.FIREFOX` ou `BrowserEnum.EDGE`, conforme sua necessidade.

---
#### Relatórios:

Após a execução, os relatórios de teste podem ser acessados:


1. Navegue até a pasta `report`.

2. Abra o arquivo `report.html`.

3. No IntelliJ IDEA:

   - Clique com o botão direito do mouse, selecione `Open in`, e escolha o navegador desejado.


4. No VSCode:

   - Clique com o botão direito do mouse, selecione `Reveal in File Explorer`, e uma janela da pasta será aberta. Clique no arquivo report.html para visualizá-lo.

#### Screenshot: 
![](src/main/resources/img/report.png)

---

#### 📊 Como Executar o Pipeline:

  

1. Acesse de Actions:

- Entre no repositório db_desafio_web - [GitHub Actions](https://github.com/loopfagundes/db_desafio_web/actions)

2. Escolha o Workflow:

- Selecione o workflow desejado na lista disponível.

  

3. Inicie o Workflow:

- Caso o workflow permita execução manual, clique em "Re-run all jobs".

- Certifique-se de selecionar a opção 'build'.

  

4. Acompanhe os Logs:

- Clique na execução ativa do workflow para visualizar os logs e monitorar o progresso.


**Observação:**

-  **Outros Workflow**: Os demais fluxos de trabalho são atualizados conforme as implementações, com o objetivo de melhorar a estrutura do código e a qualidade dos testes.

---
Feito com bug, café e muito esforço :beetle::coffee:

