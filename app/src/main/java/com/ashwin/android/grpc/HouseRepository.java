package com.ashwin.android.grpc;

import java.util.Collections;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HouseRepository {
    private static final String HOST = BuildConfig.SERVER_HOST;
    public static final int PORT = BuildConfig.SERVER_PORT;

    private final HouseServiceGrpc.HouseServiceBlockingStub stub;

    public HouseRepository() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
        stub = HouseServiceGrpc.newBlockingStub(channel);
    }

    public House getHouseById(int id) {
        HouseRequest request = HouseRequest.newBuilder().setId(id).build();
        HouseResponse response = stub.getHouse(request);
        return response.getHouse();
    }

    public List<House> getHousesBySize(int minArea) {
        HousesBySizeRequest request = HousesBySizeRequest.newBuilder().setMinArea(minArea).build();
        HousesBySizeResponse response = stub.getHousesBySize(request);
        List<Integer> ids = response.getIdsList();
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        } else {
            return getHouses(ids);
        }
    }

    public List<House> getHouses(List<Integer> ids) {
        HousesRequest request = HousesRequest.newBuilder().addAllIds(ids).build();
        HousesResponse response = stub.getHousesByIds(request);
        return response.getHousesList();
    }

    public List<House> getHouses() {
        NoParams request = NoParams.newBuilder().build();
        HousesResponse response = stub.getHouses(request);
        return response.getHousesList();
    }
}
