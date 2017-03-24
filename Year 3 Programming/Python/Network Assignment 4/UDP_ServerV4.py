import binascii
import socket
import struct
import sys
import hashlib

#To run the application use the command python3. You must use python 3.0 or higher.

UDP_IP = "127.0.0.1"
UDP_PORT = 5077
unpacker = struct.Struct('I I 8s 32s')


#Create the socket and listen
rsock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
rsock.bind((UDP_IP, UDP_PORT))

#Looping
seqCheck = 0
while True:
	#Recieve and unpack the data
	print ("Waiting for connection")
	data, addr = rsock.recvfrom(1024)
	UDP_Packet = unpacker.unpack(data)
	print ("Packet received \n")
	print ("Contents of packet are ", UDP_Packet)

	#Create a checksum to check if the packet is corrupt
	values = (UDP_Packet[0], UDP_Packet[1], UDP_Packet[2])
	packer = struct.Struct('I I 8s')
	packed_data = packer.pack(*values)
	chksum = bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")

	#Compare the checksums to test if the data is corrupt
	#Also check if the sequence number is correct
	if (UDP_Packet[3] == chksum and UDP_Packet[1] == seqCheck):


		#The packet is correct so build an ACK package
		#create checksum
		print('Checksums Match, Packet OK')
		values = (1, seqCheck, b"")
		UDP_Data = struct.Struct('I I 8s')
		packed_data = UDP_Data.pack(*values)
		chksum = bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
		#creating packet
		values = (1, seqCheck, b"", chksum)
		UDP_Packet_Data = struct.Struct('I I 8s 32s')
		UDP_Packet = UDP_Packet_Data.pack(*values)

		#We'll start waiting for the next sequence number
		if seqCheck == 1:
			seqCheck = 0
		else:
			seqCheck = 1
		print ("sequence number switched to", seqCheck)

		#Send the ACK
		sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
		sock.sendto(UDP_Packet, (UDP_IP, 5078))
		print ('Packet sent, contents: ', UDP_Packet)

	else:
		#the packet has errors so build an ACK for the previous packet
		if (UDP_Packet[3] != chksum):
			print('Checksums do not match, packet corrupt\n')
		else:
			print('Checksums match, sequence number wrong\n')
			print('sequence number is ', seqCheck)
			print('packet number is ', UDP_Packet[1])
		
		#We'll be sending the ACK for the previous sequence number
		if (seqCheck == 0):
			previousAck = 1
		else:
			previousAck = 0

		#creating the checksum
		values = (1, previousAck, b"")
		UDP_Data = struct.Struct('I I 8s')
		packed_data = UDP_Data.pack(*values)
		chksum = bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")

		#using the checksum to create a packet
		values = (1, previousAck, b"", chksum)
		UDP_Packet_Data = struct.Struct('I I 8s 32s')
		UDP_Packet = UDP_Packet_Data.pack(*values)

		#Send the ACK
		sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
		sock.sendto(UDP_Packet, (UDP_IP, 5078))
		
		

