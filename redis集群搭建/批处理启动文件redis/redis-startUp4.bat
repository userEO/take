@echo off
title redis-server
set ENV_HOME="C:\Program Files\Redis"
C:
color 0a
cd %ENV_HOME%
redis-server redis.20004.conf
exit