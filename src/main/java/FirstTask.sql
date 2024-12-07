SELECT COUNT(*)
FROM profile LEFT JOIN post ON profile.profile_id = post.profile_id
WHERE (post_id IS null);
