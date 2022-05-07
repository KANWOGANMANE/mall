//package com.sjq.cms.repository;
//
//import com.sjq.cms.entity.Song;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
///**
// * @Author Kemp
// * @create 2022/5/4 17:05
// */
//public interface SongRepository extends ElasticsearchRepository<Song,Integer> {
//    /**
//     * 根据歌手名称查询数据,如果查询的列是keyword类型,则会精确匹配
//     * @param name
//     * @return
//     */
//    List<Song> findBySongname(String name);
//
//    /**
//     * 根据歌曲名称查询数据,如果查询的列是text类型,则会默认分词查询
//     * @param name
//     * @return
//     */
//    List<Song> findByName(String name);
//}
