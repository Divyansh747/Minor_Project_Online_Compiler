FROM python 
WORKDIR /mnt
USER root
ADD . .
RUN chmod a+x ./main.py
RUN chmod a+x ./compilecode.sh
ENTRYPOINT ["./compilecode.sh"]
