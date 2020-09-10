FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER jeecgos@163.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /jeecg-cloud-user-biz

WORKDIR /jeecg-cloud-user-biz

EXPOSE 8003

ADD ./target/jeecg-cloud-user-biz-2.2.0.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar jeecg-cloud-user-biz-2.2.0.jar
