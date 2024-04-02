package org.example.projectyutobe.service;


import lombok.extern.slf4j.Slf4j;
import org.example.projectyutobe.dto.ChannelDTO;
import org.example.projectyutobe.entity.ChannelEntity;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    public String create(ChannelDTO dto, Integer id) {
        ChannelEntity channelEntity=new ChannelEntity();
        channelEntity.setName(dto.getName());
        channelEntity.setPhotoId(dto.getPhotoId());
        channelEntity.setBannerId(dto.getBannerId());
        channelEntity.setStatus(dto.getStatus());
        channelEntity.setDescription(dto.getDescription());
        channelEntity.setProfileId(id);
        channelRepository.save(channelEntity);
        return "created channel";
    }

    public String update(Integer ownerId, ChannelDTO dto, UUID uuid) {
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(uuid);
        if (optionalChannel.isEmpty()){
           log.warn("chanel xatolik");
           throw new AppBadException("Bunday channel mavjud emas");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(ownerId)){
            throw new AppBadException("Bu kanal sizga tegishli emas");
        }
        if (dto.getBannerId()!=null){
            channelEntity.setBannerId(dto.getBannerId());
        }
        if (dto.getPhotoId()!=null){
            channelEntity.setPhotoId(dto.getPhotoId());
        }
        if (dto.getDescription()!=null){
            channelEntity.setDescription(dto.getDescription());
        }
        if (dto.getName()!=null){
            channelEntity.setName(dto.getName());
        }
        if (dto.getProfileId()!=null){
            channelEntity.setProfileId(dto.getProfileId());
        }
        if (dto.getStatus()!=null){
            channelEntity.setStatus(dto.getStatus());
        }
        channelRepository.save(channelEntity);
        return "update channel";
    }

    public String updatePhoto(Integer profileId, ChannelDTO dto, UUID channelId) {
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(channelId);
        if (optionalChannel.isEmpty()){
            log.warn("not found channel");
            throw new AppBadException("not found channel");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(profileId)){
            throw new AppBadException("Sizda bunday channel mavjud emas");
        }
        channelEntity.setPhotoId(dto.getPhotoId());
        channelRepository.save(channelEntity);
        return "update channel";
    }

    public String updateBanner(Integer profileId, ChannelDTO dto, UUID channelId) {
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(channelId);
        if (optionalChannel.isEmpty()){
            log.warn("not found channel");
            throw new AppBadException("not found channel");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(profileId)){
            throw new AppBadException("Sizda bunday channel mavjud emas");
        }
        channelEntity.setBannerId(dto.getBannerId());
        channelRepository.save(channelEntity);
        return "update channel";

    }

    public PageImpl<ChannelDTO> getPaginationAdmin(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        Page<ChannelEntity>entityPage=channelRepository.findAll(pageable);
        List<ChannelEntity> entityList=entityPage.getContent();
        Long totalSize=entityPage.getTotalElements();
        List<ChannelDTO>channelDTOList=new ArrayList<>();
        for (ChannelEntity entity:entityList){
           channelDTOList.add(toDo(entity));
        }
      return new PageImpl<>(channelDTOList,pageable,totalSize);
    }
    public ChannelDTO toDo(ChannelEntity entity){
        ChannelDTO dto=new ChannelDTO();
        dto.setBannerId(entity.getBannerId());
        dto.setStatus(dto.getStatus());
        dto.setDescription(dto.getDescription());
        dto.setPhotoId(dto.getPhotoId());
        dto.setProfileId(dto.getProfileId());
        dto.setName(dto.getName());
        dto.setSubscribeCount(dto.getSubscribeCount());
        return dto;
    }

    public ChannelDTO getById(UUID channelId) {

        Optional<ChannelEntity> optionalChannel = channelRepository.findById(channelId);
        if (optionalChannel.isEmpty()){
            return new ChannelDTO();
        }
        return toDo(optionalChannel.get());
    }

    public List<ChannelDTO> changeStatus(ChannelDTO status, Integer profileId) {
        List<ChannelEntity> byProfileId = channelRepository.findByProfileId(profileId);
        if (byProfileId.isEmpty()){
            throw new AppBadException("not found channel");
        }
        List<ChannelDTO>channelDTOList=new LinkedList<>();
        for (ChannelEntity entity:byProfileId){
            if (entity.getStatus().equals(status.getStatus())){
                channelDTOList.add(toDo(entity));
            }
        }
        return channelDTOList;
    }

    public List<ChannelDTO> getUserList(Integer profileId) {
        List<ChannelDTO>channelDTOList=new ArrayList<>();
        for (ChannelEntity channelEntity : channelRepository.findByProfileId(profileId)) {
            channelDTOList.add(toDo(channelEntity));
        }
        return channelDTOList;
    }
}
