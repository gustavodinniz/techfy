# Techfy Ecommerce

## 1. Gestão de Produtos
- **CRUD de Produtos**:
    - Criar, atualizar, excluir e listar produtos.
    - **Campos básicos**: nome, descrição, preço, SKU, quantidade em estoque, categoria, imagens.
- **Categorias**:
    - CRUD de categorias para organização de produtos.
- **Atributos e Variantes**:
    - Permitir atributos dinâmicos (ex.: tamanho, cor) e variantes de produtos.

## 2. Catálogo
- **Busca de Produtos**:
    - Pesquisa com filtros (ex.: preço, categoria, atributos).
    - Suporte a pesquisa por texto (ex.: nome do produto).
- **Recomendações**:
    - Produtos relacionados ou sugestões baseadas em visualizações.

## 3. Carrinho de Compras
- **Gestão do Carrinho**:
    - Adicionar, atualizar, remover produtos no carrinho.
- **Cálculo Dinâmico**:
    - Atualizar o subtotal, descontos e total em tempo real.
- **Persistência do Carrinho**:
    - Salvar estado do carrinho por usuário (logado ou anônimo).

## 4. Sistema de Pedidos
- **Criação de Pedidos**:
    - Converter carrinho em pedido.
- **Detalhamento de Pedido**:
    - Visualizar informações do pedido, incluindo itens, valores, status.
- **Gestão de Status**:
    - Alterar status do pedido (ex.: Criado, Pago, Enviado, Entregue).
- **Histórico de Pedidos**:
    - Listar pedidos anteriores do cliente.

## 5. Pagamentos
- **Integração com Gateways**:
    - Suporte a múltiplos meios de pagamento (cartão de crédito, boleto, Pix).
- **Gestão de Pagamentos**:
    - Verificar status do pagamento (ex.: aprovado, recusado).
- **Reembolsos**:
    - Solicitar e processar reembolsos.

## 6. Gestão de Estoque
- **Atualização do Estoque**:
    - Reduzir estoque após pedidos.
    - Reabastecer estoque.
- **Monitoramento**:
    - Alertas para produtos com estoque baixo.

## 7. Sistema de Usuários
- **Cadastro e Login**:
    - Registro de usuários, autenticação e login.
- **Perfil do Usuário**:
    - Visualizar e editar informações pessoais.
- **Gestão de Endereços**:
    - Adicionar, editar e excluir endereços de entrega.

## 8. Entregas
- **Cálculo de Frete**:
    - Integração com APIs de transporte para calcular valores.
- **Gestão de Entregas**:
    - Criar e rastrear entregas associadas aos pedidos.

## 9. Relatórios e Logs
- **Relatórios de Vendas**:
    - Exibir estatísticas (ex.: total de vendas por período, produto mais vendido).
- **Monitoramento e Logs**:
    - Registrar operações importantes para auditoria.

## 10. Comunicação e Notificações
- **Notificações de Pedido**:
    - Enviar e-mails ou mensagens (ex.: pedido criado, enviado).
- **Contato com o Cliente**:
    - Integração com chat ou suporte ao cliente.

## 11. Segurança
- **Autenticação e Autorização**:
    - JWT para autenticação.
    - Perfis de usuário (ex.: admin, cliente).
- **Proteção Contra Fraudes**:
    - Verificar transações suspeitas ou bloqueadas.

---

# Arquitetura Microservices

Os serviços podem ser separados em diferentes domínios para refletir as responsabilidades acima:

1. **Product Service**:
    - Gestão de produtos, categorias e atributos.
2. **Cart Service**:
    - Gestão de carrinho.
3. **Order Service**:
    - Criação e gestão de pedidos.
4. **Payment Service**:
    - Processamento de pagamentos.
5. **User Service**:
    - Autenticação, perfil e endereços.
6. **Delivery Service**:
    - Frete e rastreamento de entregas.
7. **Notification Service**:
    - Envio de notificações (e-mails ou mensagens).

---

# Ferramentas e Tecnologias

- **Spring Boot**:
    - Framework base para os microserviços.
- **Spring Cloud**:
    - Configuração centralizada, service discovery (Eureka) e circuit breaker.
- **Spring Security**:
    - Autenticação e autorização.
- **Spring Data JPA**:
    - Persistência de dados.
- **Hibernate Validator**:
    - Validações de entrada.
- **Kafka**:
    - Mensageria para comunicação assíncrona entre microserviços.
- **Docker**:
    - Contêineres para cada microserviço.
- **PostgreSQL**:
    - Banco de dados relacional.
- **Elasticsearch**:
    - Pesquisa avançada no catálogo.
- **Redis**:
    - Cache para melhorar a performance.

---
