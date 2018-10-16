FROM node:8
WORKDIR /usr/src/app
COPY package*.json ./

RUN npm install
COPY ./dist ./dist
ADD server.js server.js
EXPOSE 8081
CMD [ "node", "server.js" ]