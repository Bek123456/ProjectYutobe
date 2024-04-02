package org.example.projectyutobe.service;


import org.example.projectyutobe.dto.PlaylistDTO;
import org.example.projectyutobe.dto.info.PlayListShortInfo;
import org.example.projectyutobe.entity.ChannelEntity;
import org.example.projectyutobe.entity.PlayListEntity;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.ChannelRepository;
import org.example.projectyutobe.repository.PlayListRepository;
import org.example.projectyutobe.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListService {
    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileRepository profileRepository;
    public String create(PlaylistDTO dto) {
        PlayListEntity entity=new PlayListEntity();
        entity.setChannelId(dto.getChannelId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setOrderNum(dto.getOrderNumber());
        entity.setStatus(dto.getStatus());
        playListRepository.save(entity);
        return "create playList";
    }


    public String update(PlaylistDTO dto, Integer profileId,Integer playListId) {
        Optional<PlayListEntity> optionalPlayList =
                playListRepository.findById(playListId);
        if (optionalPlayList.isEmpty()){
            throw new AppBadException("not found playlist");
        }
        PlayListEntity entity = optionalPlayList.get();
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(entity.getChannelId());
        if (optionalChannel.isEmpty()){
            throw new AppBadException("Not found channel");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(profileId)){
           throw new AppBadException("not found profile");
        }
        entity.setName(dto.getName());
        entity.setChannelId(dto.getChannelId());
        entity.setStatus(dto.getStatus());
        entity.setDescription(dto.getDescription());
        entity.setOrderNum(dto.getOrderNumber());
        playListRepository.save(entity);
        return "update playList";
    }

    public List<PlaylistDTO> changeStatus(PlaylistDTO dto, Integer userDetailsId) {
        List<ChannelEntity> byProfileId = channelRepository.findByProfileId(userDetailsId);
        List<PlayListEntity>playListEntities=new ArrayList<>();
        for (PlayListEntity playListEntity:playListRepository.findAll()){
            for (ChannelEntity channelEntity:byProfileId){
                if (playListEntity.getChannelId().equals(channelEntity.getId())){
                    playListEntities.add(playListEntity);
                }
            }
        }
        List<PlaylistDTO>playlistDTOS=new ArrayList<>();
        for (PlayListEntity playListEntity:playListEntities){
            if (playListEntity.getStatus().equals(dto.getStatus())){
                 playlistDTOS.add(toDo(playListEntity));
            }
        }
        return playlistDTOS;
    }
    public PlaylistDTO toDo(PlayListEntity playListEntity){
        PlaylistDTO playlistDTO=new PlaylistDTO();
        playlistDTO.setChannelId(playListEntity.getChannelId());
        playlistDTO.setDescription(playListEntity.getDescription());
        playlistDTO.setName(playListEntity.getName());
        playlistDTO.setOrderNumber(playListEntity.getOrderNum());
        playlistDTO.setStatus(playlistDTO.getStatus());
        playlistDTO.setId(playListEntity.getId());
        return playlistDTO;
    }


    public String deletedAdmin(Integer playListId) {
        playListRepository.deleteById(playListId);
        return "deleted playList";
    }

    public String deletedUser(Integer playListId, Integer profileId) {
        Optional<PlayListEntity> optionalPlayList = playListRepository.findById(playListId);
        if (optionalPlayList.isEmpty()){
            throw new AppBadException("not found playlist");
        }
        PlayListEntity playListEntity = optionalPlayList.get();
        if (playListEntity.getChannelEntity().getProfileId().equals(profileId)){
            playListRepository.deleteById(playListId);
        }
        return "deleted playList";
    }


    public PageImpl<PlayListShortInfo> playPage(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        Page<PlayListEntity> page1=playListRepository.findAll(pageable);
        List<PlayListEntity> playListEntities=page1.getContent();
        List<PlayListShortInfo>playListShortInfos=new ArrayList<>();
        for (PlayListEntity playListEntity:page1){
            playListShortInfos.add(toPage(playListEntity));
        }
        Long totalSize=page1.getTotalElements();
        return new PageImpl<>(playListShortInfos,pageable,totalSize);
    }
    public PlayListShortInfo toPage(PlayListEntity playListEntity){
       PlayListShortInfo info=new PlayListShortInfo();
       info.setId(playListEntity.getId());
       info.setName(playListEntity.getName());
       info.setDescription(playListEntity.getDescription());
       info.setOrderNumber(playListEntity.getOrderNum());
       info.setStatus(playListEntity.getStatus());
       info.setChannelId(playListEntity.getChannelId());
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(playListEntity.getChannelId());
        if (optionalChannel.isEmpty()){
            throw new AppBadException("not found channel");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        info.setChannelName(channelEntity.getName());
        info.setChannelPhotoId(channelEntity.getPhotoId());
        info.setChannelPhotoUrl(channelEntity.getAttachEntity().getPath());
        info.setProfileId(channelEntity.getProfileId());
        info.setProfileName(channelEntity.getProfileEntity().getName());
        info.setProfileSurname(channelEntity.getProfileEntity().getSurname());
        info.setProfilePhotoId(channelEntity.getPhotoId());
        info.setProfilePhotoUrl(channelEntity.getAttachEntity().getPath());
        return info;
    }

    public PageImpl<PlayListShortInfo> playPageDesc(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page-1,size, Sort.Direction.DESC,"orderNum");
        Page<PlayListEntity> page1=playListRepository.findAll(pageable);
        List<PlayListEntity> playListEntities=page1.getContent();
        List<PlayListShortInfo>playListShortInfos=new ArrayList<>();
        for (PlayListEntity playListEntity:page1){
            playListShortInfos.add(toPage(playListEntity));
        }
        Long totalSize=page1.getTotalElements();
        return new PageImpl<>(playListShortInfos,pageable,totalSize);

    }
}
