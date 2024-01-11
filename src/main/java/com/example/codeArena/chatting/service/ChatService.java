package com.example.codeArena.chatting.service;

import com.example.codeArena.chatting.domain.ChatRoom;
import com.example.codeArena.chatting.domain.Tag;
import com.example.codeArena.chatting.dto.ChatRoomCreateRequest;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private Map<String, ChatRoom> chatRooms;

    // 의존 관계 주입 완료시 실행
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 모든 채팅방 조회
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result); // 최근 생성 순
        return result;
    }

    // 채팅방 하나 조회
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    // 새로운 방 생성
    // TODO: User 연관 관계 설정 후, 입력으로 받아서 방을 만들 때 방 만든 사람 정보 추가
    public ChatRoom createRoom(ChatRoomCreateRequest dto) {
        ChatRoom chatRoom = ChatRoom.create(dto);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
