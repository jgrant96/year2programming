import binascii
import socket
import struct
import sys
import hashlib

#To run the application use the command python3. You must use python 3.0 or higher.

UDP_IP = "127.0.0.1"
UDP_PORT = 5078
unpacker = struct.Struct('I I 8s 32s')


print("UDP target IP:", UDP_IP)
print("UDP target port:", UDP_PORT)
print("\n")


def buildPacket (ack, seq, data):
	#Building the UDP checksum
	values = (ack, seq, data)
	UDP_Data = struct.Struct('I I 8s')
	packed_data = UDP_Data.pack(*values)
	chksum = bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
	#Building the UDP packet
	values = (ack, seq, data, chksum)
	UDP_Packet_Data = struct.Struct('I I 8s 32s')
	UDP_Packet = UDP_Packet_Data.pack(*values)
	return UDP_Packet

#Sending the UDP packet
def sendPacket (packet, seq, udpIP, udpPort, rsock):	
	#Loop until we recieve an ACK from the server
	sent = 0
	while sent == 0:
		unpacker = struct.Struct('I I 8s 32s')
		sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
		sock.sendto(packet, (udpIP, udpPort))
		print ("Packet sent")
		print ("Contents of sent packet: ", packet)
		print (packet[0], packet[1], packet[2], packet[3])
		print ("\n")
		
	
		#Wait for data to be recieved, and unpack it
		rsock.settimeout(0.009)
		try:
			data, addr = rsock.recvfrom(1024)
		
			UDP_PacketR = unpacker.unpack(data)
			print ("Packet recieved")
			print ("Contents of recieved packet: ", UDP_PacketR)
			print (UDP_PacketR[0], UDP_PacketR[1], UDP_PacketR[2], UDP_PacketR[3])
			values = (UDP_PacketR[0], UDP_PacketR[1], UDP_PacketR[2])
		
			#Reconstruct a checksum for comparison
			packer = struct.Struct('I I 8s')
			packed_data = packer.pack(*values)
			chksum = bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
			#If the packet is not corrupt and the packet sent by the server was an ACK
			#Then we exit the loop and the method
			if (UDP_PacketR[3] == chksum and UDP_PacketR[1] == seq):
				print ("Checksum compare result: ", UDP_PacketR[3] == chksum)
				sent = 1
				print ("\n")
			else:
				print ("Checksum compare result: ", UDP_PacketR[3] == chksum)
				print ("\n")
		except socket.timeout:
			print("Time out Occured. Resending")



sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))
packet = buildPacket(0, 0, b"NCC-1701")
sendPacket (packet, 0, UDP_IP, 5077, sock)
packet = buildPacket(0, 1, b"NCC-1664")
sendPacket (packet, 1, UDP_IP, 5077, sock)
packet = buildPacket(0, 0, b"NCC-1017")
sendPacket (packet, 0, UDP_IP, 5077, sock)
