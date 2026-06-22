#!/usr/bin/env bash
set -euo pipefail

# Where to install JavaFX in Codespaces
DEST_ROOT="/workspaces/javafx"

# URLs for SDK + JMODS (Linux x64, JavaFX 25)
SDK_URL="https://download2.gluonhq.com/openjfx/25/openjfx-25_linux-x64_bin-sdk.zip"
JMODS_URL="https://download2.gluonhq.com/openjfx/25/openjfx-25_linux-x64_bin-jmods.zip"

SDK_ZIP="openjfx-25_linux-x64_bin-sdk.zip"
JMODS_ZIP="openjfx-25_linux-x64_bin-jmods.zip"

echo "[JavaFX] Installing JavaFX 25 into ${DEST_ROOT}"
mkdir -p "${DEST_ROOT}"
cd "${DEST_ROOT}"

# Tools
sudo apt-get update -y
sudo apt-get install -y wget unzip >/dev/null
sudo apt-get install -y libgtk-3-0 libx11-6 libxtst6 libxrender1 libxi6 liboss4-salsa-asound2 libgl1 >/dev/null

# --- Download SDK ---
if [[ ! -f "${SDK_ZIP}" ]]; then
  echo "[JavaFX] Downloading SDK from ${SDK_URL}"
  wget -q -O "${SDK_ZIP}" "${SDK_URL}"
else
  echo "[JavaFX] SDK zip already present, skipping download"
fi

# --- Download JMODS ---
if [[ ! -f "${JMODS_ZIP}" ]]; then
  echo "[JavaFX] Downloading JMODS from ${JMODS_URL}"
  wget -q -O "${JMODS_ZIP}" "${JMODS_URL}"
else
  echo "[JavaFX] JMODS zip already present, skipping download"
fi

# --- Unzip SDK ---
if [[ ! -d "${DEST_ROOT}/javafx-sdk-25" ]]; then
  echo "[JavaFX] Unzipping SDK"
  unzip -q "${SDK_ZIP}"
else
  echo "[JavaFX] SDK already unzipped"
fi

# --- Unzip JMODS ---
if [[ ! -d "${DEST_ROOT}/javafx-jmods-25" ]]; then
  echo "[JavaFX] Unzipping JMODS"
  unzip -q "${JMODS_ZIP}"
else
  echo "[JavaFX] JMODS already unzipped"
fi

# Create/update stable symlinks
cd "${DEST_ROOT}"
rm -f javafx-sdk-current javafx-jmods-current
ln -s "javafx-sdk-25" "javafx-sdk-current"
ln -s "javafx-jmods-25" "javafx-jmods-current"

# Export JAVAFX_HOME (SDK root) and JAVAFX_JMODS (jmods root)
BASHRC="${HOME}/.bashrc"
SDK_PATH="${DEST_ROOT}/javafx-sdk-current"
JMODS_PATH="${DEST_ROOT}/javafx-jmods-current"

# Update ~/.bashrc with exports
grep -q 'export JAVAFX_HOME=' "${BASHRC}" \
  && sed -i "s|^export JAVAFX_HOME=.*$|export JAVAFX_HOME=${SDK_PATH}|g" "${BASHRC}" \
  || echo "export JAVAFX_HOME=${SDK_PATH}" >> "${BASHRC}"

grep -q 'export JAVAFX_JMODS=' "${BASHRC}" \
  && sed -i "s|^export JAVAFX_JMODS=.*$|export JAVAFX_JMODS=${JMODS_PATH}|g" "${BASHRC}" \
  || echo "export JAVAFX_JMODS=${JMODS_PATH}" >> "${BASHRC}"

echo "[JavaFX] JAVAFX_HOME  -> ${SDK_PATH}"
echo "[JavaFX] JAVAFX_JMODS -> ${JMODS_PATH}"
echo "[JavaFX] Done."
