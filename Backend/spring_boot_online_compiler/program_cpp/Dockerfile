FROM ubuntu_cpp
WORKDIR /mnt
USER root
ADD . .
RUN chmod a+x ./main.cpp
RUN chmod a+x ./compilecode.sh
ENTRYPOINT ["./compilecode.sh"]
