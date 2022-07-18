import org.junit.Test
import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val expected = 2
        assertEquals(expected, result)
    }

    @Test
    fun createComment() {
        val service = NoteService()
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        val result = service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val expected = 5
        assertEquals(expected, result)
    }

    @Test
    fun delete() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.delete(2)
        assertTrue(result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun deleteShouldThrow() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.delete(6)
        assertFalse(result)
    }

    @Test
    fun deleteComment() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.deleteComment(5, true)
        assertTrue(result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun deleteCommentShouldThrow() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.deleteComment(3, false)
        assertFalse(result)
    }

    @Test
    fun edit() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.edit(
            2, "My Edited First Note", "Edited Text", 1, 1, "Privacy View 1", "Privacy Comment 1"
        )
        assertTrue(result)
    }

    @Test
    fun editNotPassed() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.edit(
            6, "My Edited First Note", "Edited Text", 1, 1, "Privacy View 1", "Privacy Comment 1"
        )
        assertFalse(result)
    }

    @Test
    fun editComment() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.editComment(5, "My Edited Comment")
        assertTrue(result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun editCommentShouldThrow() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.editComment(6, "My Edited Comment")
        assertFalse(result)
    }

    @Test
    fun get() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.get(listOf(1,2,5),1, 1, 2, 0)
        assertNotNull(result)
    }

    @Test
    fun getNull() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val result = service.get(listOf(5,7,9),1, 1, 2, 0)
        assertNull(result)
    }

    @Test
    fun getById() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val expected = Note(1,"My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0)
        val result = service.getById(1,false)
        assertEquals(expected, result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun getByIdShouldThrow() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        val expected = Note(1,"My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0)
        val result = service.getById(6,false)
        assertEquals(expected, result)
    }

    @Test
    fun getComments() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment of Note-1", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment of Note-2", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment of Note-1", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment of Note-2", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment of Note-2", deleted = false, 1648924813, 0, 2, 3)
        val result = service.getComments(2,0,1,2)?.toList()
        assertNotNull(result)
    }

    @Test
    fun getCommentsNull() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment of Note-1", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment of Note-2", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment of Note-1", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment of Note-2", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment of Note-2", deleted = false, 1648924813, 0, 2, 3)
        val result = service.getComments(4,0,1,2)?.toList()
        assertNull(result)
    }

    @Test
    fun restoreComment() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.restoreComment(3)
        assertTrue(result)
    }

    @Test(expected = NoteService.NoteOrCommentException::class)
    fun restoreCommentShouldThrow() {
        val service = NoteService()
        service.add(
            "My First Note", "First Text", 0, 0, "Privacy View 1", "Privacy Comment 1",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.add(
            "My Second Note", "Second Text", 0, 0, "Privacy View 2", "Privacy Comment 2",
            needWiki = false, canComment = false, 2, 3, 1, 0
        )
        service.createComment(1, 2, "My First Comment", deleted = false, 1648924407, 1, 2, 3)
        service.createComment(2, 2, "My First Comment", deleted = false, 1648924520, 0, 2, 4)
        service.createComment(1, 2, "My Second Comment", deleted = true, 1648924699, 0, 2, 2)
        service.createComment(2, 2, "My Second Comment", deleted = true, 1648924750, 1, 2, 1)
        service.createComment(2, 2, "My Thired Comment", deleted = false, 1648924813, 0, 2, 3)
        val result = service.restoreComment(2)
        assertFalse(result)
    }
}