# Kubernetes Storage

This is a small experiment I did to see how Kubernetes Persistent
Volumes work on `minikube`.

## Prerequisite

You need to have [minikube][minikube] installed and have a running cluster.

## Experiment


```sh
kubectl apply -f persistent-volumes-minikube.yml
```

Exec into the container and write a file into the volume

```sh
kubectl exec --namespace random-storage -ti shell sh
echo HI THERE >> /var/random/experiment.txt
```

Verify that the file shows up on the `minikube` node

```sh
minikube ssh
ls -la /tmp/hostpath-provisioner/pvc-*
```

Now delete the pod and apply the `yml` file again to create a new pod.
Verify that it 

```sh
kubectl --namespace random-storage delete pod shell
# wait until it's deleted.
kubectl apply -f persistent-volumes-minikube.yml
kubectl exec --namespace random-storage -ti shell sh
ls -al /var/random
```

And you should see that the `experiment.txt` file is still there.

## Cleanup

```sh
kubectl delete namespace random-storage
```

[minikube]: https://github.com/kubernetes/minikube
