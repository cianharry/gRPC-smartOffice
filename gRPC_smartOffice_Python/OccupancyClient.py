
import grpc
from tkinter import *


# import the generated classes
import Occupancy_pb2
import Occupancy_pb2_grpc

# open a gRPC channel
channel = grpc.insecure_channel('localhost:50059')

# create a stub (client)
stub = Occupancy_pb2_grpc.OccupancyServiceStub(channel)


def occucheck(event):

    # create a valid request message
    try:
        stringnum = floorNumInput.get()
        intnum = int(stringnum)
        floor = Occupancy_pb2.OccupancyRequest(floorNum=intnum)

        response = stub.checkOccupancy(floor)

        resultLabel.config(text=response.status)

        print(response.status)
    except ValueError:
        print("Please enter a value from 1-3")
        resultLabel.config(text="Please enter a value from 1-3")


# creates a blank Tkinter window
root = Tk()
root.title("Smart Office - Occupancy Check")

mainFrame = Frame(root, width=300, height=300)
mainFrame.pack()
# create the label and place it in the window
floorLabel = Label(mainFrame, text="Please enter the floor that you wish to check: ")
floorNumInput = Entry(mainFrame)
floorBtn = Button(mainFrame, text="Check Occupancy Levels")
floorBtn.bind("<Button-1>", occucheck)
resultLabel = Label(mainFrame, text="")

floorLabel.grid(row=0)
floorNumInput.grid(row=0, column=1)
floorBtn.grid(row=1, columnspan=2)
resultLabel.grid(row=2, columnspan=2)


# loop the window display so its visible
root.mainloop()
