docker run -d \
  --name=sqlitebrowser \
  --security-opt seccomp=unconfined `#optional` \
  -e PUID=1000 \
  -e PGID=1000 \
  -e TZ=Etc/UTC \
  -p 3000:3000 \
  -p 3001:3001 \
  -v ./src/sqlite_config:/config \
  --restart unless-stopped \
  lscr.io/linuxserver/sqlitebrowser:latest