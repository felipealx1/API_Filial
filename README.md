# API_Filial
A API Filial conta com três entidades: Filial, Departamento e Funcionário. A API Filial gerencia um sitema de cadastro e controle de Filiais, Departamentos e Funcionários.<br>
Existe o relacionamento entre as entidade: Uma Filial pode ter muitos Funcionários e muitos Funcionários estão relacionados a uma Filial, e o relacinamento de um Departamento pode conter Muitos Funcionários assim como muitos Funcionários está em um Departamento.<br>
Na API Filial a respeito do cadasto do funcionário, quando o Funcionário for cadastrado ele ficará registrado na Filial e no Departamento que irá trabalhar. No cadastro e na possível atualização dos dados de um funcionário utilizamos uma API Externa (BrasilAPI - CEP) para que quando for feito o cadastro ou atualização o Funcionário irá informar o CEP e a API Externa vai fornecer seu endereço completo onde ele reside. Um dos objetivo dessa API é permitir que operações CRUD - com o uso dos métodos HTTP (GET, POST, PUT e DELETE) sejam realizadas e consumir uma API Externa. A API estará conectada ao banco de dados MySQL.<br>
Foi realizado a documentção da API com o OpenAPI - Swagger, facilitando a documentação da API e incluindo declarações claras e didáticas sobre o funcionamento da API e de suas riquisições. Para acessar o Swagger primeiro deve ser inicializado a aplicação e em seguida acessar o site http://localhost:8080/swagger-ui/index.html#/
