SELECT post.post_id FROM post
LEFT JOIN comment ON post.post_id = comment.post_id
WHERE (post.title ~ '^[0-9]' AND LENGTH(post.content) > 20)
GROUP BY post.post_id
HAVING COUNT(comment.comment_id) = 2
ORDER BY post.post_id
