import grpc
from concurrent import futures
import time

# import the generated classes
import Occupancy_pb2
import Occupancy_pb2_grpc

# import monitor function
import monitor


class OccupancyServiceServicer(Occupancy_pb2_grpc.OccupancyServiceServicer):

    def checkOccupancy(self, request, context):

        response = Occupancy_pb2.OccupancyResponse()
        response.status = monitor.occupancy_check(request.floorNum)
        return response


# create a gRPC server
server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

Occupancy_pb2_grpc.add_OccupancyServiceServicer_to_server(OccupancyServiceServicer(), server)

# listen on port 50051
print('Starting server. Listening on port 50059.')
server.add_insecure_port('[::]:50059')
server.start()

# since server.start() will not block,
# a sleep-loop is added to keep alive
try:
    while True:
        time.sleep(86400)
except KeyboardInterrupt:
    server.stop(0)