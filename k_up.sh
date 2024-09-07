#!/bin/bash

shopt -s expand_aliases
source ~/.bashrc
alias kubectl="minikube kubectl --"

# INITIALIZE CLUSTER -------------------
minikube start

echo ""; echo "----- Loading k8s... -----"; echo "";
minikube image load k8s.gcr.io/ingress-nginx/controller:v1.9.4

echo ""; echo "----- Checking addons... -----"; echo "";
minikube addons enable ingress
minikube addons enable dashboard
minikube addons enable metrics-server

echo ""; echo "----- Dashboard -----"; echo "";
echo | minikube dashboard &
# --------------------------------------

# BUILD APPLICATION --------------------
cd site-consultas/DSW-ReqB
eval $(minikube docker-env)
mvn package
eval $(minikube docker-env)
docker build . -t dsw1/site-consultas
cd ../..
# --------------------------------------

# LOAD IMAGES --------------------------
echo ""; echo "----- Loading Application -----";
minikube image load dsw1/site-consultas
echo "----- Loading MySQL -----"; echo "";
minikube image load mysql:5.7
# --------------------------------------

# SETUP DATABASE -----------------------
kubectl create -f db/pv.yaml
kubectl create -f db/pvc.yaml

kubectl apply -f db/configmap.yaml
kubectl apply -f db/secret.yaml

kubectl apply -f db/deployment.yaml
kubectl apply -f db/service.yaml
# --------------------------------------

# SETUP APPLICATION --------------------
kubectl apply -f site-consultas/deployment.yaml
kubectl apply -f site-consultas/service.yaml
# --------------------------------------

# INGRESS ------------------------------
kubectl apply -f ingress.yaml
# --------------------------------------
