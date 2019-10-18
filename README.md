# k8s-sb-postgres-end-to-end

To prepare and push image GKE:
------------------------------
% mvn -DskipTests com.google.cloud.tools:jib-maven-plugin:build -Dimage=eu.gcr.io/k8s-free-trail-254311/k8s-sb-mysql-end-to-end:v1

To deploy:
----------

% kubectl apply -f k8s.yaml

To undeploy:
------------

% kubectl delete -f k8s.yml