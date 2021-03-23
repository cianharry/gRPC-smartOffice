# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import Occupancy_pb2 as Occupancy__pb2


class OccupancyServiceStub(object):
    """Missing associated documentation comment in .proto file."""
    checkOccupancy: object

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.checkOccupancy = channel.unary_unary(
                '/OccupancyService/checkOccupancy',
                request_serializer=Occupancy__pb2.OccupancyRequest.SerializeToString,
                response_deserializer=Occupancy__pb2.OccupancyResponse.FromString,
                )


class OccupancyServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def checkOccupancy(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_OccupancyServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'checkOccupancy': grpc.unary_unary_rpc_method_handler(
                    servicer.checkOccupancy,
                    request_deserializer=Occupancy__pb2.OccupancyRequest.FromString,
                    response_serializer=Occupancy__pb2.OccupancyResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'OccupancyService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class OccupancyService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def checkOccupancy(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/OccupancyService/checkOccupancy',
            Occupancy__pb2.OccupancyRequest.SerializeToString,
            Occupancy__pb2.OccupancyResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
