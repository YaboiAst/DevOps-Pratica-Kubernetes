#!/bin/bash

shopt -s expand_aliases
source ~/.bashrc
alias kubectl="minikube kubectl --"

# INGRESS ------------------------------
kubectl delete -f ingress.yaml
# --------------------------------------

# SETUP APPLICATION --------------------
kubectl delete -f site-consultas/service.yaml
kubectl delete -f site-consultas/deployment.yaml
# --------------------------------------

# SETUP DATABASE -----------------------
kubectl delete -f db/service.yaml
kubectl delete -f db/deployment.yaml

kubectl delete -f db/configmap.yaml
kubectl delete -f db/secret.yaml
kubectl delete -f db/pv.yaml

cd db/
kubectl delete siteconsultas-pv-volumes
cd ..
# --------------------------------------

# STOP CLUSTER -------------------------
echo "Stoping minikube..."
minikube stop
echo "Clean Success"
# --------------------------------------
