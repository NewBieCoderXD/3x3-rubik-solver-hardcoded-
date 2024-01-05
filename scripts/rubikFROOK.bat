@echo off
pushd %~dp0
cd ../build
java -jar run.jar %*
popd