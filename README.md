# Neo4j example using Spring



Neo4j is a highly scalable graph database.

## 1. Neo4j docker

Download the image:
```bash
$ docker pull neo4j
```

Create a volume to store the data:
```bash
$ docker volume create neo4j-vol
```

Start a container:
```bash
$ docker run --name my-neo4j -p 7474:7474 -p 7687:7687 -v neo4j-vol:/data -d neo4j
```

Which allow you to access neo4j through your browser at http://localhost:7474

The volume is bound to /data to allow the database to be persisted outside the container.

By default, this requires you to login with neo4j/neo4j and change the password.

To change the password via command line:
```bash
$ curl -v -u neo4j:neo4j -X POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}"
```

This change the password from neo4j to secret.

# 2. Implementation

Based on [Fraud Detection Example](https://neo4j.com/developer/).

## Direct requests

You can get all persons with http://localhost:8080/api/v1/persons

Or a single person via http://localhost:8080/api/v1/persons/hank

## Transitive closure

Given suspicions about hank, [find related information](http://localhost:8080/api/v1/fraud/information/hank) to investigate.

Hank shares an account with Abby. Abby shares a SSN with Sophie and Max.

## Investigation targeting

Fraud rings often share fraudulent identifying information.

Any person with connections to [more than two entities](http://localhost:8080/api/v1/fraud/connections) in the graph are suspicious.

Sophie, Max and Abby all share a SSN. Hank is also suspicious because he is sharing an account with Abby.

## Fast insights

Given that we identified SSN 993-63-2634 as suspicious, [find all associated accounts](http://localhost:8080/api/v1/fraud/associated/993632634).

We see that the Cayman account #863 is the only account where a Person using this SSN owns the account.
