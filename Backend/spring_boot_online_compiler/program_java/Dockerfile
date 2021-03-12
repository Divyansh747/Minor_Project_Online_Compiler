FROM openjdk
WORKDIR /mnt
USER root
ADD . .
RUN chmod a+x ./main.java
RUN chmod a+x ./compilecode.sh
ENTRYPOINT ["./compilecode.sh"]
