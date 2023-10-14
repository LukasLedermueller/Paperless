FROM nginx

COPY ./static /usr/share/nginx/html

COPY custom.conf /etc/nginx/conf.d/custom.conf

RUN chown -R nginx /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
