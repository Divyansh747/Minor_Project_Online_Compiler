FROM node:alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm install -g npm@7.16.0
#RUN npm install
COPY . ./
RUN npm install && npm run build
#ENTRYPOINT ["npm", "start"]

FROM nginx:1.17.0-alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
