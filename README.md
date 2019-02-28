# OpenCensus Demo

* Start Prometheus with: `prometheus --config.file=etc/prometheus.yaml`

* Start Grafana on OSX with: 

```bash
brew services start grafana
```

```bash
grafana-server --config=/usr/local/etc/grafana/grafana.ini --homepath /usr/local/share/grafana cfg:default.paths.logs=/usr/local/var/log/grafana cfg:default.paths.data=/usr/local/var/lib/grafana cfg:default.paths.plugins=/usr/local/var/lib/grafana/plugins
```
