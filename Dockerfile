FROM adoptopenjdk/openjdk11:centos

WORKDIR /
COPY ./ .

RUN curl https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm -o /tmp/google-chrome-stable_current_x86_64.rpm
RUN yum localinstall /tmp/google-chrome-stable_current_x86_64.rpm

RUN ./gradlew :projects:svelte:test