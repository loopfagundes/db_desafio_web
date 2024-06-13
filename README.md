
## Desafio Tecnico DBServer

## [Bug Bank](https://bugbank.netlify.app/)

>O banco com bugs e falhas do seu jeito.
>
> É um _Bug Bank_, para que? É pratique de testes e automação de testes.

_O projeto é exclusivamente para [DBServer](https://db.tec.br/)_

---

### Ferramentas que foi ulitizado?

#### Construido com a estrutura:

:pushpin: [MAVEN](https://maven.apache.org/download.cgi),

:pushpin: [JAVA 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

#### Dependencias:
:pushpin: [TestNG](https://mvnrepository.com/artifact/org.testng/testng/7.10.2),  

:pushpin: [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.21.0),  

:pushpin: [WebDriverManager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/5.8.0),

:pushpin: [Extent Reports TestNG Adapter](https://mvnrepository.com/artifact/com.aventstack/extentreports-testng-adapter/1.0.3)

---

### Baixando o projeto:

Para baixar o projeto, abrir o comando ou dentro IDE:
```bash
git clone https://github.com/loopfagundes/db_desafio_web.git
```

#### Como executar?
Para executar na sua máquina local, o comando ou através de sua IDE:
```bash
mvn test
```
Padrão do browser Chrome.
Para executar no Firefox ou Edge,  caminho para acessar de pastas dentro IDE:
```sh
src > 
	main > 
		java > 
			app.netlify.bugbank >
				utils > 
					BaseTest
					
@BeforeMethod  
DriverFactory.createInstance(BrowserEnum.CHROME);
```
`BrowserEnum.Chrome` ".Chrome" tem opção para Firefox ou Edge.

---
Feito com bug, café e muito esforço :beetle::coffee:

