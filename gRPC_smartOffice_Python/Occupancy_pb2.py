# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: Occupancy.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='Occupancy.proto',
  package='',
  syntax='proto3',
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\x0fOccupancy.proto\"$\n\x10OccupancyRequest\x12\x10\n\x08\x66loorNum\x18\x01 \x01(\x05\"#\n\x11OccupancyResponse\x12\x0e\n\x06status\x18\x02 \x01(\t2M\n\x10OccupancyService\x12\x39\n\x0e\x63heckOccupancy\x12\x11.OccupancyRequest\x1a\x12.OccupancyResponse\"\x00\x62\x06proto3'
)




_OCCUPANCYREQUEST = _descriptor.Descriptor(
  name='OccupancyRequest',
  full_name='OccupancyRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='floorNum', full_name='OccupancyRequest.floorNum', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=19,
  serialized_end=55,
)


_OCCUPANCYRESPONSE = _descriptor.Descriptor(
  name='OccupancyResponse',
  full_name='OccupancyResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='status', full_name='OccupancyResponse.status', index=0,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=57,
  serialized_end=92,
)

DESCRIPTOR.message_types_by_name['OccupancyRequest'] = _OCCUPANCYREQUEST
DESCRIPTOR.message_types_by_name['OccupancyResponse'] = _OCCUPANCYRESPONSE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

OccupancyRequest = _reflection.GeneratedProtocolMessageType('OccupancyRequest', (_message.Message,), {
  'DESCRIPTOR' : _OCCUPANCYREQUEST,
  '__module__' : 'Occupancy_pb2'
  # @@protoc_insertion_point(class_scope:OccupancyRequest)
  })
_sym_db.RegisterMessage(OccupancyRequest)

OccupancyResponse = _reflection.GeneratedProtocolMessageType('OccupancyResponse', (_message.Message,), {
  'DESCRIPTOR' : _OCCUPANCYRESPONSE,
  '__module__' : 'Occupancy_pb2'
  # @@protoc_insertion_point(class_scope:OccupancyResponse)
  })
_sym_db.RegisterMessage(OccupancyResponse)



_OCCUPANCYSERVICE = _descriptor.ServiceDescriptor(
  name='OccupancyService',
  full_name='OccupancyService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_start=94,
  serialized_end=171,
  methods=[
  _descriptor.MethodDescriptor(
    name='checkOccupancy',
    full_name='OccupancyService.checkOccupancy',
    index=0,
    containing_service=None,
    input_type=_OCCUPANCYREQUEST,
    output_type=_OCCUPANCYRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
])
_sym_db.RegisterServiceDescriptor(_OCCUPANCYSERVICE)

DESCRIPTOR.services_by_name['OccupancyService'] = _OCCUPANCYSERVICE

# @@protoc_insertion_point(module_scope)
