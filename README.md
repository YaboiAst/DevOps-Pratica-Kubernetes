# DevOps - Prática Kubernetes
## Integrantes
- Vitor Gabriel Orsin - 801575


## Sobre o projeto
### Site de Agendamento de Consultas
A aplicação Spring simula um sistema de agendamento de consultas, contendo os seguintes papéis:
- **Admin**: Responsável por cadastrar e admnistrar novos médicos e pacientes no sistema;
- **Medico**: Cadastro do profissional que pode aceitar consultas pelo sistema;
- **Paciente**: Cadastro do cliente, que pode visualizar suas próximas consultas e agendar ali mesmo;
  
A **consulta** pode ser marcada por um paciente, que pode escolher o médico que vai atendê-lo e o horário;

### Banco de dados - SQL
Imagem do banco de dados SQL para admnistrar a permanência de dados;

## Como usar
1. Clone o repositório
2. Na pasta raiz do repositorio, dê direitos de execução para os scripts .sh
```
chmod +x k_up/sh
chmod +x k_down/sh
```
3. Use esses scripts para iniciar ou finalizar a aplicação
```
# Inicializar
./k_up.sh

# Finalizar
./k_down.sh
```
Para acessar o site, acesse o site pelo endereço: siteconsultas.k8s.local 
