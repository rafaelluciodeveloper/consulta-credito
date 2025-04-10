# Etapa 1: Build do Angular
FROM node:20-alpine AS builder

WORKDIR /app

# Copia os arquivos de dependência
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia o restante da aplicação
COPY . .

# Build do projeto Angular
RUN npm run build -- --project=creditos-web --configuration=production

# Etapa 2: Servir via Nginx
FROM nginx:1.27.4-alpine-slim

# Remove config default do nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia o build gerado na etapa anterior para o Nginx
COPY --from=builder /app/dist/creditos-web/browser /usr/share/nginx/html

# Copia sua configuração customizada do Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
