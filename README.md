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

# 2.