package com.example.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowRelation {
    private int beFollowedId;
    private int followerId;
}
