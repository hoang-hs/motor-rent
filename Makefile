remove:
	docker rmi $(docker images | grep 'dashboard')
run:
	docker build -t dashboard .
	docker-compose up

push:
	docker build -t rent-motor .
	docker tag rent-motor:latest hoanghs120/rent-motor:latest
	docker push hoanghs120/rent-motor:latest