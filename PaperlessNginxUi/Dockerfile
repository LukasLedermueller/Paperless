FROM nginx

COPY ./static /usr/share/nginx/html

COPY cors.conf /etc/nginx/conf.d/cors.conf

RUN chown -R nginx /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
