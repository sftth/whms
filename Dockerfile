FROM centos:7
RUN yum install java -y
RUN ["/bin/bash","-c","mkdir -p /sorc001/sftth318/applications"]
RUN ["/bin/bash","-c","mkdir /engn001"]
RUN ["/bin/bash","-c","mkdir -p /logs001/sftth318/springboot"]
RUN chmod 777 -R /logs001/

ADD ./target/whms-1.0.jar /sorc001/sftth318/applications/
#ADD ./openshift/whms-1.0.jar /sorc001/sftth318/applications/
ADD ./restart.sh /engn001/

WORKDIR /engn001
RUN ["/bin/bash","-c","chmod 755 restart.sh"]
ENTRYPOINT ["/bin/bash", "/engn001/restart.sh"]