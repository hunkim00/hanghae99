package com.sparta.week03.controller;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoRepository;
import com.sparta.week03.domain.MemoRequestDto;
import com.sparta.week03.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/memos/{id}")
    public  Long updateMemo(@PathVariable Long id, String password, @RequestBody MemoRequestDto requestDto){
        memoService.update(id, password, requestDto);
        return id;
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoRepository.deleteById(id);
        return id;
    }

    @GetMapping("/posts/{id}")
    public Memo readOnePost(@PathVariable Long id) {
        return memoRepository.findById(id).orElse(null);
    }


}