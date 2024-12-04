# **PlanoService**

## **Descrição Geral**
O **PlanoService** é um microserviço responsável por gerenciar assinaturas de planos de empresas e beneficiários no sistema de Dente Convênio. Ele também permite a definição e gestão de planos e benefícios, além de configurar os benefícios que um consultório cobre.

---

## **Objetivo**
Gerenciar a assinatura de empresas e beneficiários, fornecendo funcionalidades para cadastrar planos e benefícios, além de configurar coberturas específicas para consultórios.

---

## **Principais Recursos**
1. **Cadastro de Planos**:
   - Permite criar novos planos disponíveis no sistema.
   - Cada plano pode conter uma lista de benefícios associados.

2. **Cadastro de Benefícios**:
   - Gerencia os benefícios que podem ser incluídos em um plano.
   - Exemplo de benefícios: consultas odontológicas, exames, procedimentos específicos.

3. **Gerenciamento de Assinaturas**:
   - Permite vincular empresas e beneficiários a planos disponíveis.
   - Controle de validade e status das assinaturas.

4. **Definição de Cobertura**:
   - Configura os benefícios que um consultório pode oferecer dentro dos planos.

---

## **Tecnologias Utilizadas**

### **Linguagem e Frameworks**
- **Java 17**: Linguagem principal para desenvolvimento do serviço.
- **Spring Security**: Para autenticação e autorização.
- **Spring Web**: Para construção de APIs REST.

### **Banco de Dados**
- **MySQL**: Banco de dados relacional utilizado para persistência das informações de planos, benefícios e assinaturas.

### **Infraestrutura e Comunicação**
- **Eureka**: Para descoberta e registro de serviços.
- **Spring Cloud Gateway**: Para roteamento de requisições entre os microserviços.
- **RabbitMQ**: Para comunicação assíncrona entre microserviços.

### **Desempenho e Gerenciamento**
- **Cache**: Para otimizar consultas frequentes de planos e benefícios.
- **Spring Scheduled**: Para tarefas agendadas, como verificar expiração de assinaturas ou enviar notificações.

---
