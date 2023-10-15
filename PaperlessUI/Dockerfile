# Build Application using Node
FROM node:16-alpine AS build

WORKDIR /app

COPY . .

RUN npm install

RUN npm run build

# Serve Application using Nginx Server
FROM nginx:alpine

COPY --from=build ./app/dist/paperless-ngx/ /usr/share/nginx/html

COPY custom.conf /etc/nginx/conf.d/custom.conf

RUN chown -R nginx /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

