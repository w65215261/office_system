package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.NoteAttachmentDao;
import com.pmcc.soft.week.dao.TaskAttachmentDao;
import com.pmcc.soft.week.domain.NoteAttachment;
import com.pmcc.soft.week.domain.TaskAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangbin on 2016/3/25.
 */
@Transactional
@Service
public class NoteAttachmentManager {
    @Autowired
    NoteAttachmentDao noteAttachmentDao;

    public int insert(NoteAttachment noteAttachment){
        noteAttachment.setId(UUIDGenerator.getUUID());
        return noteAttachmentDao.insert(noteAttachment);
    }

    public int deleteAttachment(NoteAttachment noteAttachment){
        return noteAttachmentDao.deleteAttachment(noteAttachment);
    }

    public List<NoteAttachment> findNoteAttachmentByNoteOid(NoteAttachment noteAttachment) {
        return noteAttachmentDao.findNoteAttachmentByNoteOid(noteAttachment);

    }


}
