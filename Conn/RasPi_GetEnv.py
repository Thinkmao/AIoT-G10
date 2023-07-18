import paho.mqtt.client as mqttclient

# MQTT
def on_connect(client, userdata, flags, rc):
	if rc == 0:
		print("Connected to MQTT Broker!")
	else:
		print('Failed to connect, return code %d', rc)
		
def sendmsg(topic, data):
	msg = f'{data}'
	result = 0