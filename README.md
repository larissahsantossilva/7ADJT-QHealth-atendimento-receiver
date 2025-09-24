# 📥 QHealth – Atendimento Receiver  
Serviço responsável por **receber e iniciar atendimentos**, atuando como ponto de entrada para a criação de novos registros de atendimento.

## 📝 Visão Geral  
O **QHealth – Atendimento Receiver** é um microserviço que centraliza o início de um atendimento dentro do ecossistema QHealth.  
Ele permite criar novos registros de atendimento e listar atendimentos em andamento, garantindo que cada atendimento seja iniciado de forma organizada e monitorada.

Este serviço funciona como **ponto de recebimento de requisições** que dão início a toda a orquestração de filas e processos internos do QHealth.

## 🛠 Funcionalidades  
- 🆕 **Criar Atendimento**: inicia um novo atendimento e retorna o `UUID` gerado.  
- 🔎 **Listar Atendimentos**: busca todos os atendimentos em andamento.  
- ✅ **Validação de dados de entrada**: garante integridade e consistência das informações.  
- 🧪 **Logs e Testes**: monitora requisições e possui cobertura de testes para confiabilidade.

## 🚀 Tecnologias Principais  
- **Java Spring Boot** – desenvolvimento da API REST.  
- **Spring Data JPA** – persistência de dados.  
- **Swagger/OpenAPI** – documentação clara e interativa dos endpoints.  
- **JUnit** – criação e execução de testes automatizados.  
- **Docker** – containerização e deploy simplificado.
