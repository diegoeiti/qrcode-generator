# 🚀 QR Code Generator API
API REST desenvolvida para gerar QR Codes dinâmicos, realizar o upload automático para a nuvem (AWS S3) e retornar uma URL pública para acesso imediato.

## 📋 Sobre o Projeto
O projeto foi desenvolvido seguindo as melhores práticas de mercado, focando em escalabilidade e desacoplamento. A aplicação recebe um texto ou URL, transforma em uma imagem QR Code utilizando a biblioteca ZXing e gerencia o armazenamento de forma persistente no Amazon S3.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 21 (LTS)
* **Framework:** Spring Boot 3
* **Storage:** Amazon S3 (AWS SDK for Java v2)
* **Containerização:** Docker & Dockerfile (Multi-stage build)
* **Arquitetura:** Clean Architecture / Ports & Adapters
* **Geração de QR:** Google ZXing

## 🏗️ Arquitetura
A aplicação utiliza o padrão **Ports and Adapters** para garantir que a lógica de negócio seja independente de infraestrutura.
* **ports:** Interfaces que definem como a aplicação se comunica com o mundo externo (Storage).
* **adapters:** Implementações concretas (S3StorageAdapter).
* **service:** Regra de negócio e orquestração.

## 🚀 Como Executar

### Pré-requisitos
* Docker instalado
* Conta AWS com um Bucket S3 criado

### 1. Configuração das Credenciais
Crie um arquivo `.env` na raiz do projeto com suas chaves da AWS:
```env
AWS_ACCESS_KEY_ID=sua_access_key
AWS_SECRET_ACCESS_KEY=sua_secret_key
AWS_REGION=us-east-1
AWS_BUCKET_NAME=nome-do-seu-bucket
```

2. Build da Imagem Docker
```
docker build -t qrcode-generator:1.0 .
``` 
3. Rodar o Container
```
docker run -p 8080:8080 --env-file .env qrcode-generator:1.0
``` 

## 🔌 Endpoints
### Gerar QR Code
`POST /qrcode`

**Request Body:**
```JSON
{
"text": "https://www.linkedin.com/in/seu-perfil"
}
```
Response (200 OK):
```JSON
{
"url": "https://seu-bucket.s3.us-east-1.amazonaws.com/uuid-aleatorio.png"
}
```
## 🛡️ Segurança
**Este projeto utiliza boas práticas de segurança:**
* **Variáveis de Ambiente:** Nenhuma chave de acesso (Access Keys) está exposta no código-fonte.
* **Docker Multi-stage Build:** Garante uma imagem final mais leve e segura, contendo apenas o JRE necessário para execução.

Projeto desenvolvido para fins de estudo; o storage temporário pode estar offline.

Desenvolvido por **Diego Eiti Nakashima Fortunato**